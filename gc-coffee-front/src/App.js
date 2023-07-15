import './App.css';
import 'bootstrap/dist/css/bootstrap.css'
import React, {useEffect, useState} from "react";
import {ProductList} from "./components/ProductList";
import {Summary} from "./components/Summary";
import axios from "axios";

function App() {
  const [products, setProducts] = useState([
    {id: 'uuid-1', productName: '콜롬비아 커피', category: '커피빈', price: '3000'},
    {id: 'uuid-2', productName: '에티오피아 커피', category: '커피빈', price: '4000'},
    {id: 'uuid-3', productName: '브라질 커피', category: '커피빈', price: '5000'},
  ]);

  const [items, setItems] = useState([]);

  const handleAddClicked = productId => {
    const product = products.find(v => v.productId === productId)
    const found = items.find(v => v.productId === productId);

    const updatedItems = found ? items.map(v => (v.productId === productId) ? {...v, count: v.count + 1} : v) : [...items, {...product, count: 1}];
    setItems(updatedItems);
  }

  const handleOrderSubmit = order => {
    if (items.length === 0) {
      alert("아이템을 추가해주세요!");
    } else {
      const data = {
        email: order.email,
        address: order.address,
        postcode: order.postcode,
        orderItems: items.map(v => ({
          productId: v.productId,
          category: v.category,
          price: v.price,
          quantity: v.count
        }))
      };

      axios.post('http://localhost:8080/api/v1/orders', data)
      .then(v => alert("주문이 정상적으로 처리되었습니다."), e => {
        alert("SERVER Error !");
        console.error(e);
      })
    }
  }

  useEffect(() => {
    axios.get('http://localhost:8080/api/v1/products')
      .then(v => setProducts(v.data))
  }, []);

  return (
      <div className="container-fluid">
        <div className="row justify-content-center m-4">
          <h1 className="text-center">Grids & Circle</h1>
        </div>

        <div className="card">
          <div className="row">
            <ProductList products={products} onAddClick={handleAddClicked}/>
            <Summary items={items} onOrderSubmit={handleOrderSubmit}/>
          </div>
        </div>
      </div>
  );
}

export default App;

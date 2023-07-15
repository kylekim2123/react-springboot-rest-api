import {Product} from "./Product";
import React from "react";

export function ProductList({ products, onAddClick }) {
  return (
      <div
          className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
        <h5 className="flex-grow-0">
          <b>상품 목록</b>
        </h5>

        <ul className="list-group products">
          {products.map(v =>
              <li key={v.productId} className="list-group-item d-flex mt-3">
                {/*<Product productId={v.productId} productName={v.productName} category={v.category} price={v.price}/>*/}
                <Product {...v} onAddClick={onAddClick}/>
              </li>
          )}
        </ul>
      </div>
  )
}
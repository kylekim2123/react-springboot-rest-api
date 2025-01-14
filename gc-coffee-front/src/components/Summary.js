import {SummaryItem} from "./SummaryItem";
import React, {useState} from "react";

export function Summary({ items, onOrderSubmit }) {
  const totalPrice = items.reduce((prev, cur) => prev + (cur.price * cur.count), 0)
  const [order, setOrder] = useState({ email: "", address: "", postcode: "" });

  const handleEmailInputChanged = e => setOrder({ ...order, email: e.target.value });
  const handleAddressInputChanged = e => setOrder({ ...order, address: e.target.value });
  const handlePostcodeInputChanged = e => setOrder({ ...order, postcode: e.target.value });

  const handleSubmit = e => {
    if (order.address === "" || order.email === "" || order.postcode === "") {
      alert("입력 값을 확인해주세요!");
    } else {
      onOrderSubmit(order);
    }
  }

  return (
      <div className="col-md-4 summary p-4">
        <div>
          <h5 className="m-0 p-0">
            <b>Summary</b>
          </h5>
        </div>

        <hr/>

        {items.map(v => <SummaryItem key={v.productId} count={v.count} productName={v.productName}/>)}

        <form>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">이메일</label>
            <input type="email" className="form-control mb-1" id="email" value={order.email} onChange={handleEmailInputChanged}/>
          </div>

          <div className="mb-3">
            <label htmlFor="address" className="form-label">주소</label>
            <input type="text" className="form-control mb-1" id="address" value={order.address} onChange={handleAddressInputChanged}/>
          </div>

          <div className="mb-3">
            <label htmlFor="postcode" className="form-label">우편번호</label>
            <input type="text" className="form-control" id="postcode" value={order.postcode} onChange={handlePostcodeInputChanged}/>
          </div>

          <div>당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>
        </form>

        <div className="row pt-2 pb-2 border-top">
          <h5 className="col">총금액</h5>
          <h5 className="col text-end">{totalPrice}원</h5>
        </div>

        <button onClick={handleSubmit} className="btn btn-dark col-12">결제하기</button>
      </div>
  )
}
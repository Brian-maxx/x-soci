import { useState, useEffect } from 'react';
import '../styles/Products.css'; // Import CSS

function Practice() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8000/api/products/')
      .then(res => res.json())
      .then(data => setProducts(data))
      .catch(err => console.error("Lỗi khi lấy dữ liệu: ", err));
  }, []);

  return (
    <div className="products-container">
      <h2>Danh sách Sản phẩm</h2>
      <div className="products-grid">
        {products.map(product => (
          <div key={product.id} className="product-card">
            <h3 className="product-name">{product.name}</h3>
            <p className="product-price">
              {product.price.toLocaleString('vi-VN')} VNĐ
            </p>
            <button className="add-to-cart-btn">Thêm vào giỏ</button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Practice;
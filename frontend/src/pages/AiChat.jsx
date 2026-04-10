import { useState, useEffect } from 'react';
import '../styles/AiChat.css'; // Import CSS
import { IoAddCircleOutline } from "react-icons/io5";
import { BsFillSendFill } from "react-icons/bs";
import AiChatHistory from '../components/AiChatHistory';

function AiChat() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8000/api/products/')
      .then(res => res.json())
      .then(data => setProducts(data))
      .catch(err => console.error("Lỗi khi lấy dữ liệu: ", err));
  }, []);

  return (
    <div className="px-6 pb-6 !mx-0 w-full">
      <div className="ai-chat-container">
        <div className="chat-history glass-liquid">
          <div className="history-title">Lịch sử trò chuyện</div>
          <AiChatHistory />
        </div>
        <div className="chat-container glass-liquid">
          <div className="chat-interface">
          
          </div>
          <div className="chat-input">
            <div className="input-feature">
                <IoAddCircleOutline className="feature-icon" />
            </div>
            <input className='input-textarea' placeholder='Nhập tin nhắn...'></input>
            <button className='input-btn'><BsFillSendFill /></button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AiChat;
import '../../styles/Practice.css'; // Import CSS
import { CiHome, CiBoxList } from "react-icons/ci";
import { PiRanking } from "react-icons/pi";
import { useState } from 'react';
import { LuRepeat2, LuYoutube, LuFileText } from "react-icons/lu";
import { TiFlashOutline } from "react-icons/ti";
import { IoAddSharp } from "react-icons/io5";
import PracticeModeContainer from '../../components/user/PracticeModeContainer';

function Practice() {
  const [activeIndex, setActiveIndex] = useState(0);
  const navItems = [
    { icon: <CiHome />, label: 'Trang chủ' },
    { icon: <CiBoxList />, label: 'Bài tập' },
    { icon: <PiRanking />, label: 'Xếp hạng' },
  ];
  const handleClick = (index) => {
    setActiveIndex(index);
  };

  return (
    <div className="px-6 pb-6 !mx-0 w-full">
      <div className="glass-liquid">
        <div className="practice-container">
          <h2 className="practice-header">
            <div className="practice-title">
              <h1>Xin chào, <span className="gradient-text">TrungBT</span></h1>
              <p>Bạn đã sẵn sàng học thêm từ mới chưa?</p>
            </div>
            <div>
              <span className="gradient-text">Level up with QuizaX</span>
              <div className="practice-components">
                {navItems.map((item, index) => (
                  <div
                    key={index}
                    className={index === activeIndex ? 'glass-active' : ''}
                    onClick={() => handleClick(index)}
                  >
                    {item.icon}
                  </div>
                ))}
              </div>
            </div>
          </h2>
          <div className="practice-item">
            <div className="mode-container">
              <div className="mode-header">
                <h2 className="mode-title">
                  <TiFlashOutline className="mode-icon" />
                  Chế độ học
                </h2>
                <button className="add-word-btn">
                  <IoAddSharp /> Thêm từ
                </button>
              </div>
              <div className="practice-mode">
                <div className="mode meaning-mode">
                  <PracticeModeContainer title="Ý nghĩa" description="Ghi nhớ các từ vựng" />
                  <div className='wrapper-icon wrapper-icon-meaning-mode'>
                    <LuRepeat2 className="icon" />
                  </div>
                </div>
                <div className="mode contextualized-mode">
                  <PracticeModeContainer title="Ngữ cảnh" description="Điền vào chỗ còn thiếu" />
                  <div className='wrapper-icon wrapper-icon-contextualized-mode'>
                    <LuFileText className="icon" />
                  </div>
                </div>
                <div className="mode shadowing-mode">
                  <PracticeModeContainer title="Luyện nghe" description="Nghe ngẫu nhiên và lặp lại" />
                  <div className='wrapper-icon wrapper-icon-shadowing-mode'>
                    <LuYoutube className="icon" />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Practice;
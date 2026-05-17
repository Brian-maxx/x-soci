import { Link, Outlet, useLocation } from 'react-router-dom'
import '../../styles/Header.css'
import userIcon from '../../assets/img/user.png'


function Header() {
    const location = useLocation();

    return (
        <>
            <header className="p-6">
                <div className="header-container glass-liquid">
                    <div className='header-logo'>
                        <Link to='/social' className='logo-text'>X-SO</Link>
                    </div>
                    <nav className='header-nav-links rounded-2xl text-xl'>
                        <Link to="/social" className={`nav-link ${location.pathname === "/social" ? "glass-active" : ""}`}>Cộng đồng</Link>
                        {/* <Link to="/explore" className={`nav-link ${location.pathname === "/explore" ? "active" : ""}`}>Khám phá</Link> */}
                        <Link to="/ai-chat" className={`nav-link ${location.pathname === "/ai-chat" ? "glass-active" : ""}`}>AI Chat</Link>
                        <Link to="/practice" className={`nav-link ${location.pathname === "/practice" ? "glass-active" : ""}`}>Luyện từ</Link>
                    </nav>
                    <div className='header-profile'>
                        <img src={userIcon} alt="User Profile" className='profile-icon' />
                    </div>
                </div>
            </header>
            <Outlet />
        </>
    )
}

export default Header
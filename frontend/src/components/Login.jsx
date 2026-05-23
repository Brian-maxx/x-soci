import '../styles/Auth.css';
import { useState } from 'react';
import { login } from '../api/authApi';
import { showSuccess, showError } from "../utils/alert";
import { useNavigate } from "react-router-dom";

function Login({ onSwitch, onForgotPassword }) {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
            email: '',
            password: '',
        });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await login(formData);
            await showSuccess(navigate, "Đăng nhập thành công", "Chào mừng bạn quay lại!", "/social");
        } catch (error) {
            await showError(navigate, "Đăng nhập thất bại", "Sai tài khoản hoặc mật khẩu!");
        }
    };

    return (
        <div className='login-container'>
            <h1 className='login-title'>Đăng nhập</h1>

            <form className='login-form' onSubmit={handleSubmit}>
                <div className='login-input-container'>
                    <label className='login-label'>Tài khoản</label>
                    <input type='text' placeholder='Username hoặc Email' className='login-input' name='email' value={formData.email} onChange={handleChange} />
                </div>

                <div className='login-input-container'>
                    <label className='login-label'>Mật khẩu</label>
                    <input type='password' placeholder='Mật khẩu' className='login-input' name='password' value={formData.password} onChange={handleChange} />
                </div>
                <div className='login-forgot-password-container'>
                    <a className='login-forgot-password-link' onClick={onForgotPassword}>Quên mật khẩu?</a>
                </div>

                <div className='login-button-container'>
                    <button type='submit' className='login-button' onClick={handleSubmit}>Đăng nhập</button>
                    <div className='login-register-container'>
                        <p>hoặc</p>
                        <button type='button' onClick={onSwitch} className='login-register-link'>
                            Đăng ký
                        </button>
                    </div>
                </div>
            </form>
        </div>
    );
}

export default Login;
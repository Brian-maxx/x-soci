import '../styles/Auth.css';
import { useState } from 'react';
import { register } from '../api/authApi';
import { showSuccess, showError } from "../utils/alert";
import { useNavigate } from "react-router-dom";

function Register({ onSwitch }) {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        email: '',
        username: '',
        password: '',
        confirmPassword: '',
        phone: '',
        address: '',
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
            const response = await register(formData);
            await showSuccess(navigate, "Đăng ký thành công", "Hãy đăng nhập nhé!", "/login");
            onSwitch();
        } catch (error) {
            await showError(navigate, "Đăng ký thất bại", "Vui lòng kiểm tra lại thông tin!");
        }
    };

    return (
        <>
            <div className='register-container'>
                <h1 className='register-title'>Đăng ký</h1>

                <form className='register-form' onSubmit={handleSubmit}>
                    <div className='register-form-container'>

                        <div className='register-input-container'>
                            <label className='register-label'>Email</label>
                            <input
                                type='email'
                                name='email'
                                placeholder='Email'
                                className='register-input'
                                value={formData.email}
                                onChange={handleChange}
                            />
                        </div>

                        <div className='register-input-container'>
                            <label className='register-label'>Tên người dùng</label>
                            <input
                                type='text'
                                name='username'
                                placeholder='Tên người dùng'
                                className='register-input'
                                value={formData.username}
                                onChange={handleChange}
                            />
                        </div>

                        <div className='register-input-container'>
                            <label className='register-label'>Mật khẩu</label>
                            <input
                                type='password'
                                name='password'
                                placeholder='Mật khẩu'
                                className='register-input'
                                value={formData.password}
                                onChange={handleChange}
                            />
                        </div>

                        <div className='register-input-container'>
                            <label className='register-label'>Xác nhận mật khẩu</label>
                            <input
                                type='password'
                                name='confirmPassword'
                                placeholder='Xác nhận mật khẩu'
                                className='register-input'
                                value={formData.confirmPassword}
                                onChange={handleChange}
                            />
                        </div>

                        <div className='register-input-container'>
                            <label className='register-label'>Số điện thoại</label>
                            <input
                                type='text'
                                name='phone_number'
                                placeholder='Số điện thoại'
                                className='register-input'
                                value={formData.phone_number}
                                onChange={handleChange}
                            />
                        </div>

                        <div className='register-input-container'>
                            <label className='register-label'>Địa chỉ</label>
                            <input
                                type='text'
                                name='address'
                                placeholder='Địa chỉ'
                                className='register-input'
                                value={formData.address}
                                onChange={handleChange}
                            />
                        </div>

                    </div>

                    <div className='register-btn-container'>
                        <button type='submit' onClick={handleSubmit} className='register-button'>Đăng ký</button>

                        <div className='register-login-container'>
                            <p>Đã có tài khoản?</p>
                            <button type='button' onClick={onSwitch} className='login-link'>
                                Đăng nhập
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </>
    );
}

export default Register;
import '../styles/Auth.css';

function Register({ onSwitch }) {
    return (
        <div className='register-container'>
            <h1 className='register-title'>Đăng ký</h1>

            <form className='register-form'>
                <div className='register-form-container'>

                    <div className='register-input-container'>
                        <label className='register-label'>Email</label>
                        <input type='email' placeholder='Email' className='register-input' />
                    </div>

                    <div className='register-input-container'>
                        <label className='register-label'>Tên người dùng</label>
                        <input type='text' placeholder='Username' className='register-input' />
                    </div>

                    <div className='register-input-container'>
                        <label className='register-label'>Mật khẩu</label>
                        <input type='password' placeholder='Mật khẩu' className='register-input' />
                    </div>

                    <div className='register-input-container'>
                        <label className='register-label'>Xác nhận mật khẩu</label>
                        <input type='password' placeholder='Nhập lại mật khẩu' className='register-input' />
                    </div>

                    <div className='register-input-container'>
                        <label className='register-label'>Số điện thoại</label>
                        <input type='text' placeholder='Số điện thoại' className='register-input' />
                    </div>

                    <div className='register-input-container'>
                        <label className='register-label'>Địa chỉ</label>
                        <input type='text' placeholder='Địa chỉ' className='register-input' />
                    </div>

                </div>

                <div className='register-btn-container'>
                    <button type='submit' className='register-button'>Đăng ký</button>

                    <div className='register-login-container'>
                        <p>Đã có tài khoản?</p>
                        <button type='button' onClick={onSwitch} className='login-link'>
                            Đăng nhập
                        </button>
                    </div>
                </div>
            </form>
        </div>
    );
}

export default Register;
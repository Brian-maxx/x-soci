import '../styles/Auth.css';

function Login({ onSwitch, onForgotPassword }) {
    return (
        <div className='login-container'>
            <h1 className='login-title'>Đăng nhập</h1>

            <form className='login-form'>
                <div className='login-input-container'>
                    <label className='login-label'>Tài khoản</label>
                    <input type='text' placeholder='Username hoặc Email' className='login-input' />
                </div>

                <div className='login-input-container'>
                    <label className='login-label'>Mật khẩu</label>
                    <input type='password' placeholder='Mật khẩu' className='login-input' />
                </div>
                <div className='login-forgot-password-container'>
                    <a className='login-forgot-password-link' onClick={onForgotPassword}>Quên mật khẩu?</a>
                </div>

                <div className='login-button-container'>
                    <button type='submit' className='login-button'>Đăng nhập</button>
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
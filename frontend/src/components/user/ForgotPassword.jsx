import '../../styles/Auth.css';

function ForgotPassword({ onSwitch }) {
    return (
        <div className='forgot-password-container'>
            <h1 className='forgot-password-title'>Quên mật khẩu</h1>
            <form className='forgot-password-form'>
                <div className='forgot-password-form-container'>
                    <div className='forgot-password-input-container'>
                        <label className='forgot-password-label'>Email</label>
                        <input type='email' placeholder='Email' className='forgot-password-input' />
                    </div>
                </div>

                <div className='forgot-password-btn-container'>
                    <button type='submit' className='forgot-password-button'>Gửi</button>
                    <div className='forgot-password-login-container'>
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

export default ForgotPassword;
import { useState } from 'react';
import Login from '../../components/user/Login';
import Register from '../../components/user/Register';
import ForgotPassword from '../../components/user/ForgotPassword';

function Auth() {
    const [isLogin, setIsLogin] = useState(true);
    const [isForgotPassword, setIsForgotPassword] = useState(false);

    const handleSwitch = () => {
        setIsLogin(!isLogin);
        setIsForgotPassword(false);
    };

    const handleForgotPassword = () => {
        setIsForgotPassword(true);
        setIsLogin(false);
    }

    return (
        <div className='h-screen w-screen flex items-center justify-center bg-cover bg-center bg-no-repeat'>
            {isLogin 
                ? <Login onSwitch={handleSwitch} onForgotPassword={handleForgotPassword} />
                : isForgotPassword
                    ? <ForgotPassword onSwitch={handleSwitch} />
                    : <Register onSwitch={handleSwitch} />
            }
        </div>
    );
}

export default Auth;
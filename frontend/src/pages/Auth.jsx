import { useState } from 'react';
import Login from '../components/Login';
import Register from '../components/Register';
import ForgotPassword from '../components/ForgotPassword';

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
        <div className='h-screen w-screen flex items-center justify-center bg-[url("/src/assets/background.png")] bg-cover bg-center bg-no-repeat'>
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
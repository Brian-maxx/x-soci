import { MdOutlineClear } from "react-icons/md";
import { IoIosCheckmarkCircleOutline } from "react-icons/io";
import { useEffect } from "react";

export default function Snackbar({ isActive,message, type, onClose, timeOutAuto = 3000 }) {

    useEffect(() => {
        const timer = setTimeout(() => {
            onClose();
        }, timeOutAuto)

        return () => {
            clearTimeout(timer);
        }
    }, [onClose]);

    return (
        <div className={`${isActive ? 'flex' : 'hidden'} ${type} bg-green-200 fixed top-4 right-4 z-50 gap-2 items-center justify-between px-4 py-3 rounded shadow-lg text-black rounded-lg transition-all delay-150 duration-500 ease-in-out`}>
            <IoIosCheckmarkCircleOutline className="text-[24px] text-green-600" />
            <div className="flex items-center gap-4">
                <span>{message}</span>
                <button className='close-btn' onClick={onClose}>
                    <MdOutlineClear className="text-[20px] cursor-pointer" />
                </button>
            </div>
        </div>
    );
}
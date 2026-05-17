import React from 'react';
import Swal from 'sweetalert2';

function Alert({ type, message, onClose }) {
    const showAlert = () => {
        Swal.fire({
        icon: 'success',
        title: 'Saved!',
        text: 'User data has been updated.',
        timer: 2000, // Optional: auto-closes after 2 seconds
        showConfirmButton: false
        });
    };
    return (
        <button onClick={showAlert}>Submit Data</button>
    );
}
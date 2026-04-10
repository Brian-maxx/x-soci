import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";

export const showSuccess = async (navigate, title, text, redirectTo = "/") => {
  await Swal.fire({
    icon: "success",
    title,
    text,
    timer: 2000,
    showConfirmButton: false,
  }).then(() => {
    navigate(redirectTo);
  });
};

export const showError = async (navigate, title, text, redirectTo = "/") => {
  await Swal.fire({
    icon: "error",
    title,
    text,
  }).then(() => {
    navigate(redirectTo);
  });
};

export const showConfirm = async (navigate, title, text, redirectTo = "/") => {
  return await Swal.fire({
    icon: "warning",
    title,
    text,
    showCancelButton: true,
    confirmButtonText: "OK",
    cancelButtonText: "Hủy",
  }).then(() => {
    if (redirectTo && Swal.isConfirmed()) {
      navigate(redirectTo);
    }
  });
};
const showAlert = (title, text, icon, focusElement = null) => {
  return Swal.fire({
    title: title,
    text: text,
    icon: icon,
    confirmButtonText: 'OK'
  }).then((result) => {
    if (result.isConfirmed && focusElement) {
      focusElement.focus();
    }
  });
}

const checkRegisterForm = (formData) => {
  if (!formData.userId) {
    showAlert('Error!', '아이디를 입력해주세요', 'error', $("#inputFirstName"));
    return false;
  }
  if (!formData.userName) {
    showAlert('Error!', '이름을 입력해주세요', 'error', $("#inputLastName"));
    return false;
  }
  if (!formData.password) {
    showAlert('Error!', '비밀번호를 입력해주세요', 'error', $("#inputPassword"));
    return false;
  }
  return true;
}
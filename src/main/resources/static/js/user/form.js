const showAlert = async (title, text, icon, focusElement = null) => {
    await Swal.fire({
        title: title, text: text, icon: icon, confirmButtonText: 'OK', didClose: () => {
            $('[aria-hidden="true"]').removeAttr('aria-hidden');
            if (focusElement) {
                setTimeout(() => {
                    $(focusElement).focus();
                }, 100);
            }
        }
    });
};


const checkRegisterForm = async (formData) => {
    try {
        if (!formData.userId) {
            await showAlert('Error!', '아이디를 입력해주세요', 'error', $("#inputUserId"));
            return false;
        }
        if (!formData.userName) {
            await showAlert('Error!', '이름을 입력해주세요', 'error', $("#inputLastName"));
            return false;
        }
        if (!formData.password) {
            await showAlert('Error!', '비밀번호를 입력해주세요', 'error', $("#inputPassword"));
            return false;
        }
        return true;
    } catch (e) {
        console.error(e.message)
    }
}

const checkLoginForm = async (formData) => {
    try {
        if (!formData.userId) {
            await showAlert('Error!', '아이디를 입력해주세요', 'error', $("#inputUserId"));
            return false;
        }
        if (!formData.password) {
            await showAlert('Error!', '비밀번호를 입력해주세요', 'error', $("#inputPassword"));
            return false;
        }
        return true;
    } catch (e) {
        console.error(e.message)
    }
}


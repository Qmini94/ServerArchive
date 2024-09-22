import { showAlert } from '/js/common.js';

export const handleUserAjax = () => {
    const createUser = async () => {
        try {
            const formData = {
                userId: $("#inputUserId").val().replace(/\s+/g, ''),
                userName: $("#inputLastName").val().replace(/\s+/g, ''),
                email: $("#inputEmail").val().replace(/\s+/g, ''),
                department: $("#inputDepartment").val().replace(/\s+/g, ''),
                position: $("#inputPosition").val().replace(/\s+/g, ''),
                phone: $("#inputPhone").val().replace(/\s+/g, ''),
                password: $("#inputPassword").val().replace(/\s+/g, ''),
                level: $("#inputLevel").val().replace(/\s+/g, '')
            };

            const isValid = await checkRegisterForm(formData);
            if (!isValid) {
                return false;
            }

            $.ajax({
                type: "POST",
                url: "/api/user/register",
                data: JSON.stringify(formData),
                contentType: 'application/json; charset=utf-8',
                success: (res) => {
                    showAlert('Success!', '회원가입 완료', 'success').then(() => {
                        window.location = '/user/list';
                    });
                },
                error: (error) => {
                    showAlert('Error!', '회원가입 실패', 'error');
                    console.error(error);
                }
            });
        } catch (e) {
            console.error(e.message);
            throw e;
        }
    };

    const loginUser = async () => {
        try {
            const formData = {
                userId: $("#inputUserId").val().replace(/\s+/g, ''),
                password: $("#inputPassword").val().replace(/\s+/g, ''),
                isRememberPassword: $("#inputRememberPassword").is(":checked"),
                opt: $("#inputOtp").val().replace(/\s+/g, '')
            };

            const isValid = await checkLoginForm(formData);
            if (!isValid) {
                return false;
            }

            $.ajax({
                type: "POST",
                url: "/api/user/login",
                data: JSON.stringify(formData),
                contentType: 'application/json; charset=utf-8',
                success: (res) => {
                    const {result, data: {userName} = {}} = res;
                    let title = result === 'SUCCESS' ? 'Success!' : 'Error!';
                    let alertMessage = result === 'SUCCESS' ? `${userName}님 반갑습니다.` : '로그인 도중 문제가 발생했습니다.';
                    let alertType = result === 'SUCCESS' ? 'success' : 'error';
                    let redirectUrl = result === 'SUCCESS' ? '/user/list' : '/user/login';

                    showAlert(title, alertMessage, alertType).then(() => {
                        if (redirectUrl) {
                            window.location = redirectUrl;
                        }
                    });
                },
                error: (error) => {
                    showAlert('Error!', '로그인 도중 오류가 발생했습니다.', 'error');
                    console.error(error);
                }
            });
        } catch (e) {
            console.error(e.message);
            throw e;
        }
    };

    const checkDuplicateUid = async () => {
        try {
            const userId = $("#inputUserId").val();

            if (userId === '') {
                await showAlert('Error!', '아이디를 입력해주세요', 'error', $("#inputUserId"));
                return false;
            }

            $.ajax({
                type: "POST",
                url: "/api/user/check-uid",
                data: JSON.stringify(userId),
                contentType: 'application/json; charset=utf-8',
                success: (res) => {
                    const {data: {available, message} = {}} = res;
                    if (available) {
                        showAlert('Success!', message, 'success').then(() => {
                            const checkBtn = $('.check-userId-btn');
                            checkBtn.removeClass('btn-light').addClass('btn-success');
                            checkBtn.data('checked', true);
                            checkBtn.text('확인완료');
                        });
                    } else {
                        showAlert('Error!', message, 'warning', $("#inputUserId"));
                    }
                },
                error: (error) => {
                    showAlert('Error!', '아이디 조회 오류', 'error');
                    console.error(error);
                }
            });

        } catch (e) {
            console.error(e.message);
            throw e;

        }
    };

    return {createUser, loginUser, checkDuplicateUid};
};

const checkRegisterForm = async (formData) => {
    try {
        const isCheckDuplicateUid = $(".check-userId-btn").data('checked') === true;

        if (!formData.userId) {
            await showAlert('Error!', '아이디를 입력해주세요', 'error', $("#inputUserId"));
            return false;
        }

        if (!isCheckDuplicateUid) {
            await showAlert('Error!', '아이디 중복 확인을 해주세요', 'error', $("#inputUserId"));
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
        console.error(e.message);
    }
};

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
        console.error(e.message);
    }
};
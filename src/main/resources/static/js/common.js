export const startSessionTimer = () => {
    try {
        const currentPage = window.location.pathname;
        const excludedPages = ['/', '/user/login'];

        if (excludedPages.includes(currentPage)) {
            return;
        }

        const token = getCookie('token');
        if (!token) {
            alert('접근권한이 없습니다.');
            window.location.href = '/user/login';
            return;
        }

        const decodedToken = jwt_decode(token);
        const currentTime = Math.floor(Date.now() / 1000);  // 현재 시간 (초 단위)
        const timeElapsed = currentTime - decodedToken.iat;  // 발급된 지 경과된 시간 (초 단위)
        const timeRemaining = decodedToken.exp - currentTime;  // 남은 시간 (초 단위)

        // 1분마다 유효성 검사
        const checkInterval = setInterval(() => {
            const now = Math.floor(Date.now() / 1000);
            if (now >= decodedToken.exp) {
                console.log('토큰이 만료되었습니다.');
                clearInterval(checkInterval);  // 현재 타이머 중단
                logout();
            } else if (timeRemaining <= 60 && timeElapsed >= 50 * 60) {  // 50분 경과, 10분 남음
                clearInterval(checkInterval);  // 현재 타이머 중단
                const agreeMessage = confirm('10분 후에 자동으로 로그아웃됩니다. 로그인을 유지하겠습니까?');
                if (agreeMessage) {
                    renewToken();  // 토큰 갱신
                } else {
                    logout();
                }
            }
        }, 60 * 1000);  // 1분마다 토큰 체크

    } catch (e) {
        console.error('Error:', e.message);
    }
};

const renewToken = () => {
    fetch('/api/user/renew-token', {
        method: 'POST',
    })
      .then(response => response.json())
      .then(({ token }) => {
          if (token) {
              startSessionTimer();  // 타이머 재시작
          } else {
              alert('로그인 연장에 오류가 발생했습니다. 다시 로그인해주세요.');
              logout();
          }
      })
      .catch((error) => {
          console.error('Error:', error.message);
          logout();
      });
};

export const logout = () => {
    document.cookie = 'token=; Max-Age=0; path=/;';
    window.location.href = '/';
};

const getCookie = (name) => {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
    return null;
};

export const showAlert = async (title, text, icon, focusElement = null) => {
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

export const handleInputFormat = () => {

    /**
     * 000-0000-0000 자동 포맷
     * @param input
     */
    const formatPhoneNumber = (input) => {
        try {
            let numbers = input.value.replace(/[^0-9]/g, '');
            if (numbers.length > 11) {
                numbers = numbers.slice(0, 11);
            }
            if (numbers.length > 8) {
                input.value = numbers.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
            } else if (numbers.length > 3) {
                input.value = numbers.replace(/(\d{3})(\d{4})?/, '$1-$2');
            } else {
                input.value = numbers;
            }
        } catch (e) {
            console.error(e.message);
            throw e;
        }
    };

    /**
     * 000-000-0000 자동 포맷
     * @param input
     */
    const formatOfficeNumber = (input) => {
        try {
            let numbers = input.value.replace(/[^0-9]/g, '');
            if (numbers.length > 10) {
                numbers = numbers.slice(0, 10);
            }
            if (numbers.length > 6) {
                input.value = numbers.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
            } else if (numbers.length > 3) {
                input.value = numbers.replace(/(\d{3})(\d{3})?/, '$1-$2');
            } else {
                input.value = numbers;
            }
        } catch (e) {
            console.error(e.message);
            throw e;
        }
    };

    const allowOnlyAlphaAndNumeric = (input) => {
        try {
            input.value = input.value.replace(/[^a-zA-Z0-9]/g, '');
        } catch (e) {
            console.error(e.message);
            throw e;
        }
    };

    return { formatPhoneNumber, formatOfficeNumber, allowOnlyAlphaAndNumeric };
};
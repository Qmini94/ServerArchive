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

        // 토큰 갱신을 5~10분 전에 랜덤하게 수행
        const renewalThreshold = (5 + Math.floor(Math.random() * 6)) * 60;

        const checkTokenAndRenew = () => {
            const now = Math.floor(Date.now() / 1000);
            const remaining = decodedToken.exp - now;

            if (remaining <= renewalThreshold) {
                renewToken();
                document.removeEventListener('click', checkTokenAndRenew);
                document.removeEventListener('keydown', checkTokenAndRenew);
                document.removeEventListener('scroll', checkTokenAndRenew);
            }
        };
        document.addEventListener('click', checkTokenAndRenew);
        document.addEventListener('keydown', checkTokenAndRenew);
        document.addEventListener('scroll', checkTokenAndRenew);

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
              alert('세션이 만료되었습니다. 다시 로그인해주세요.');
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
    window.location.href = '/user/login';
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
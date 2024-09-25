import { formatPhoneNumber } from '/js/common.js';
import { handleUserAjax } from '/js/user/ajax.js';

const userAjax = handleUserAjax();

export const handleUserRegisterForm = () => {
    try {
        $("#inputUserId").on('input', function () {
            const checkBtn = $('.check-userId-btn');
            checkBtn.removeClass('btn-success').addClass('btn-light');
            checkBtn.data('checked', false);
            checkBtn.text('중복확인');
        });

        $(".check-userId-btn").on('click', function () {
            userAjax.checkDuplicateUid();
        });

        $(".btn-register").on('click', function (e) {
            e.preventDefault();
            $('#inputEmail').val(getFullEmail());
            userAjax.createUser();
        });

        $("#inputPhone").on('input', function (e) {
            formatPhoneNumber(e.target);
        });

        $('#emailDomainSelect').on('change', toggleCustomDomainInput);
    } catch (e) {
        console.error(e.message);
        throw e;
    }
};

export const handleUserLoginForm = () => {
    try {
        $(".btn-login").on('click', function (e) {
            e.preventDefault();
            userAjax.loginUser();
        });
    } catch (e) {
        console.error(e.message);
        throw e;
    }
};

const toggleCustomDomainInput = () => {
    try {
        const selectElement = $('#emailDomainSelect').val();
        const customDomainInput = $('#customDomainInput');
        selectElement === 'custom' ? customDomainInput.show() : customDomainInput.hide();
    } catch (e) {
        console.error(e.message);
        throw e;
    }
};

const getFullEmail = () => {
    const emailId = $('#inputEmailId').val();
    let emailDomain = $('#emailDomainSelect').val();

    if (emailDomain === 'custom') {
        emailDomain = $('#inputCustomDomain').val();
    }
    return `${emailId}${emailDomain}`;
};
import { formatPhoneNumber } from '/js/common.js';
import { handleUserAjax } from '/js/user/ajax.js';

$(function () {
    const userAjax = handleUserAjax();
    $(".btn-register").on('click', function (e) {
        e.preventDefault();
        $('#inputEmail').val(getFullEmail());
        userAjax.createUser();
    });
    $("#inputPhone").on('input', function (e) {
        formatPhoneNumber(e.target);
    });
    $('#emailDomainSelect').on('change', toggleCustomDomainInput);
});

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
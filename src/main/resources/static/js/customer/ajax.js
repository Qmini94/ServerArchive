import { showAlert } from '/js/common.js';

export const viewCustomer = async () => {
    $('.customer-update').on('click', function () {
        const idx = $(this).data('idx');
        window.location.href = `/customer/update/${idx}`;
    });

    $('.customer-delete').on('click', async function () {
        const idx = $(this).data('idx');

        try {
            const res = await $.ajax({
                type: 'DELETE',
                url: `/api/customer/delete/${idx}`
            });
            showAlert('Success!', res.message, 'success').then(() => {
                window.location = '/customer/list';
            });
        } catch (error) {
            showAlert('Error!', error, 'error');
            console.error(error);
        }
    });
}

export const createCustomer = async () => {
    $('#save').on('click', async function (e) {
        e.preventDefault();

        try {
            const idx = $('input[name=\'idx\']').val();
            const mode = $('input[name=\'mode\']').val();
            const url = mode === 'create' ? '/api/customer/create' : `/api/customer/update/${idx}`;
            const params = {
                companyName: $('#companyName').val(),
                serverIdx: $('#serverIdx').val(),
                serviceIdx: $('#serviceIdx').val(),
                managers: $('#managers').val(),
                codeType: $('#codeType').val(),
                memo: $('#memo').val(),
                registrantId: $('#registrantId').val()
            };

            const res = await $.ajax({
                type: mode === 'create' ? 'POST' : 'PUT',
                url: url,
                data: JSON.stringify(params),
                contentType: 'application/json; charset=utf-8'
            });
            showAlert('Success!', res.message, 'success').then(() => {
                window.location = '/customer/list';
            });
        } catch (error) {
            showAlert('Error!', error, 'error');
            console.error(error);
        }
    });
};
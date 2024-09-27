import { showAlert } from '/js/common.js';

export const viewCustomer = async () => {
    $('.customer-row').on('click', function () {
        const idx = $(this).find('td').first().text();
        window.location.href = `/customer/update/${idx}`;
    });
    $('.customer-delete').on('click', async function (e) {
        e.preventDefault();
        e.stopPropagation();
        const idx = $(this).data('idx');

        $.ajax({
            type: 'DELETE',
            url: `/api/customer/delete/${idx}`,
            success: function (res) {
                alert(res.message);
                window.location = '/customer/list';
            },
            error: function (error) {
                alert(error);
                console.error(error);
            }
        });
    });
}
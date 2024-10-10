export const handleUserList = () => {
    try {
        $('.user-info').on('click', function () {
            const idx = $(this).data('idx');
            window.location.href = `/user/update/${idx}`;
        });

    } catch (e) {
        console.error(e.error);
        throw e;
    }
};
export const renderPagination = (currentPage, totalElements, size, baseUrl) => {
    const totalPages = Math.ceil(totalElements / size);
    const $paginationContainer = $('#pagination-container');
    $paginationContainer.empty(); // Clear existing content

    const pageItems = [];

    const prevDisabled = currentPage === 1 ? 'disabled' : '';
    const prevHref = currentPage > 1 ? `${baseUrl}?page=${currentPage - 1}&size=${size}` : '#';
    pageItems.push(`<li class="page-item ${prevDisabled}"><a class="page-link" href="${prevHref}" tabindex="-1">Prev</a></li>`);

    for (let i = 1; i <= totalPages; i++) {
        const activeClass = i === currentPage ? 'active' : '';
        pageItems.push(`<li class="page-item ${activeClass}"><a class="page-link" href="${baseUrl}?page=${i}&size=${size}">${i}</a></li>`);
    }

    const nextDisabled = currentPage === totalPages ? 'disabled' : '';
    const nextHref = currentPage < totalPages ? `${baseUrl}?page=${currentPage + 1}&size=${size}` : '#';
    pageItems.push(`<li class="page-item ${nextDisabled}"><a class="page-link" href="${nextHref}">Next</a></li>`);

    $paginationContainer.append(`<ul class="pagination justify-content-center">${pageItems.join('')}</ul>`);
};
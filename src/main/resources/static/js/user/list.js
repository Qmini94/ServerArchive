import { renderBasicSearchBox } from '/js/fragments/search.js';
import { getUserList } from '/js/user/ajax.js';
import { renderPagination } from '/js/fragments/pagination.js';

export const handleUserList = async () => {
    try {
        setupSearchOptions();       // 검색 목록
        await displayUserList();    // 회원 리스트
        viewDetailUserInfo();       // 상세보기

    } catch (e) {
        console.error(e.error);
        throw e;
    }
};

const setupSearchOptions = () => {
    const options = [
        { value: 'userId', label: '아이디' },
        { value: 'userName', label: '회원 이름' },
        { value: 'role', label: '권한' },
    ];
    renderBasicSearchBox(options);
};

const displayUserList = async () => {
    const res = await getUserList();
    const { content, pageable, totalElements, size } = res;
    const { pageNumber } = pageable;

    await renderListHtml(content);
    await renderPagination(pageNumber + 1, totalElements, size, '/user/list');
};

const renderListHtml = (list) => {
    try {
        const $memberTableBody = $('#memberTableBody');
        $memberTableBody.empty(); // 기존 내용을 비움

        if (list.length === 0) {
            const emptyRow = `
        <tr>
          <td colspan="9" class="text-center">데이터가 없습니다.</td>
        </tr>
      `;
            $memberTableBody.append(emptyRow);
        } else {
            const fragment = $(document.createDocumentFragment()); // Document Fragment 사용

            list.forEach((user, index) => {
                const rowHtml = `
                  <tr data-idx="${user.idx}" class="user-info">
                    <th scope="row">${list.length - index}</th>
                    <td>${user.userId}</td>
                    <td>${user.userName}</td>
                    <td>${user.department}</td>
                    <td>${user.position}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>${user.level}</td>
                    <td>${user.lastLogin}</td>
                  </tr>
        `;
                fragment.append(rowHtml);
            });

            $memberTableBody.append(fragment);
        }
    } catch (e) {
        console.error(e.message);
    }
};

const viewDetailUserInfo = () => {
    $('.user-info').on('click', function () {
        const idx = $(this).data('idx');
        window.location.href = `/user/update/${idx}`;
    });
};
import { validateSearchKey } from '/js/validate_form.js';

export function submit() {
    document.getElementById('searchForm').addEventListener('submit', function(event) {
        event.preventDefault();

        const searchInput = document.getElementById('searchKey');
        const searchKey = searchInput.value.trim();

        if (!validateSearchKey(searchKey)) {
            alert('검색 키는 한글, 영문, 숫자 또는 공백으로만 이루어져야 합니다.');
            return false;
        }

        searchInput.value = searchKey;
        this.submit();
    });
}


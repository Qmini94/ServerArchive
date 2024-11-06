export const renderBasicSearchBox = (options) => {
  try {
    const searchHtml = `
        <div class="row mb-4 justify-content-end align-items-center">
            <div class="col-auto">
                <label for="filterSelect" class="visually-hidden">검색 필터 선택</label>
                <select id="filterSelect" class="form-select form-select-sm" aria-label="검색 필터 선택">
                    ${options.map(option => `
                        <option value="${option.value}">${option.label}</option>
                    `).join('')}
                </select>
            </div>
            <div class="col-auto">
                <label for="searchInput" class="visually-hidden">검색어 입력</label>
                <input type="text" id="searchInput" class="form-control form-control-sm" placeholder="검색어를 입력하세요"
                    aria-label="검색어 입력">
            </div>
            <div class="col-auto">
                <button class="btn btn-primary btn-sm" id="searchBtn">검색</button>
            </div>
        </div>
    `;
    $('#search-container').html(searchHtml);
  } catch (e) {
    console.error(e.message);
  }
};
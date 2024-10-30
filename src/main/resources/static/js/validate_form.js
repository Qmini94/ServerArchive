/**
 * 000-0000-0000 자동 포맷
 * @param input
 */
export const formatPhoneNumber = (input) => {
    try {
        let numbers = input.value.replace(/[^0-9]/g, '');
        if (numbers.length > 11) {
            numbers = numbers.slice(0, 11);
        }
        if (numbers.length > 8) {
            input.value = numbers.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
        } else if (numbers.length > 3) {
            input.value = numbers.replace(/(\d{3})(\d{4})?/, '$1-$2');
        } else {
            input.value = numbers;
        }
    } catch (e) {
        console.error(e.message);
        throw e;
    }
};

/**
 * 000-000-0000 자동 포맷
 * @param input
 */
export const formatOfficeNumber = (input) => {
    try {
        let numbers = input.value.replace(/[^0-9]/g, '');
        if (numbers.length > 10) {
            numbers = numbers.slice(0, 10);
        }
        if (numbers.length > 6) {
            input.value = numbers.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
        } else if (numbers.length > 3) {
            input.value = numbers.replace(/(\d{3})(\d{3})?/, '$1-$2');
        } else {
            input.value = numbers;
        }
    } catch (e) {
        console.error(e.message);
        throw e;
    }
};

/**
 * YYYY-mm-dd 자동 포맷
 * @param input
 */
export const formatDate = (input) => {
    try {
        let value = input.value.replace(/\D/g, '');

        let year = value.substring(0, 4);
        let month = value.substring(4, 6);
        let day = value.substring(6, 8);

        let formattedDate = year;
        if (month) formattedDate += `-${month}`;
        if (day) formattedDate += `-${day}`;

        input.value = formattedDate;
    } catch (e) {
        console.error(e.message);
        throw e;
    }
};

/**
 * 세자리수 마다 , 자동 포맷
 * @param input
 * @returns {string}
 */
export const formatNumberWithCommas = (input) => {
    try {
        let numberString = input.toString();
        return numberString.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    } catch (e) {
        console.error(e.message);
        throw e;
    }
};

export const allowOnlyAlphaAndNumeric = (input) => {
    try {
        input.value = input.value.replace(/[^a-zA-Z0-9]/g, '');
    } catch (e) {
        console.error(e.message);
        throw e;
    }
};

export const allowOnlyAlpha = (input) => {
    try {
        input.value = input.value.replace(/[^a-zA-Z]/g, '');
    } catch (e) {
        console.error(e.message);
        throw e;
    }
};

export const allowOnlyNumeric = (input) => {
    try {
        input.value = input.value.replace(/[^0-9]/g, '');
    } catch (e) {
        console.error(e.message);
        throw e;
    }
};

export const allowOnlyKorean = (input) => {
    try {
        input.value = input.value.replace(/[^가-힣ㄱ-ㅎㅏ-ㅣ]/g, '');
    } catch (e) {
        console.error(e.message);
        throw e;
    }
};

export const validateURL = (input) => {
    try {
        const urlPattern = /^(http:\/\/|https:\/\/)([a-z0-9-]+\.)+[a-z]{2,}(\/[^\s]*)?$/i;
        return urlPattern.test(input.value.trim());
    } catch (e) {
        console.error(e.message);
        throw e;
    }
};

export const validateSearchKey = (input) => {
    try {
        const searchKeyPattern = /^[a-zA-Z0-9가-힣\s]+$/;
        return searchKeyPattern.test(input.value.trim());
    } catch (e) {
        console.error(e.message);
        throw e;
    }
};
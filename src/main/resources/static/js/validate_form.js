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

export const allowOnlyAlphaAndNumeric = (input) => {
    try {
        input.value = input.value.replace(/[^a-zA-Z0-9]/g, '');
    } catch (e) {
        console.error(e.message);
        throw e;
    }
};
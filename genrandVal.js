// cypress/support/utils.js

// Function to generate a random alphanumeric string of a specified length
export const generateRandomString = (length) => {
    let result = '';
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const charactersLength = characters.length;
    for (let i = 0; i < length; i++) {
        result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
};

// Function to generate a random string of letters only (uppercase and lowercase)
export const generateRandomLetters = (length) => {
    let result = '';
    const letters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
    const lettersLength = letters.length;
    for (let i = 0; i < length; i++) {
        result += letters.charAt(Math.floor(Math.random() * lettersLength));
    }
    return result;
};

//Function to generate a random string of numbers
export const generateRandomDigitString = (length) => {
    let result = '';
    const digits = '0123456789';
    const digitsLength = digits.length;
    for (let i = 0; i < length; i++) {
        result += digits.charAt(Math.floor(Math.random() * digitsLength));
    }
    return result;
};

import axios from 'axios';

const API_URL = '/api';

export const register = async (userData) => {
    try {
        const response = await axios.post(`${API_URL}/user/`, userData);

        return response.data;
    } catch (error) {
        console.log("FULL ERROR:", error);

        throw error.response?.data || { message: "Server không phản hồi" };
    }
};

export const login = async (credentials) => {
    try {
        const response = await axios.post(`${API_URL}/user/login/`, credentials);
        return response.data;
    } catch (error) {
        console.log("FULL ERROR:", error);

        throw error.response?.data || { message: "Server không phản hồi" };
    }
};
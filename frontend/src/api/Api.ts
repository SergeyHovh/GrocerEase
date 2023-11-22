// create an axios instance to access backend api

import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:9001',
    headers: {
        'Content-Type': 'application/json'
    },
});

export default api;

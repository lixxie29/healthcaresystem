import axios from 'axios';

const API_URL = 'http://localhost:8080'; // Adjust the port if necessary

const getDoctors = async () => {
    const response = await axios.get(`${API_URL}/doctors`);
    return response.data;
};

const registerDoctor = async (doctorData) => {
    const response = await axios.post(`${API_URL}/register_doctor`, doctorData);
    return response.data;
};

const findDoctorById = async (id) => {
    const response = await axios.get(`${API_URL}/doctors/${id}`);
    return response.data;
};

const findDoctorByEmail = async (email) => {
    const response = await axios.get(`${API_URL}/find_doctor_email/${email}`);
    return response.data;
};

const loginDoctor = async (loginData) => {
    const response = await axios.post(`${API_URL}/login_doctor`, loginData);
    return response.data;
};

const updateDoctor = async (id, doctorData) => {
    const response = await axios.put(`${API_URL}/update_doctor/${id}`, doctorData);
    return response.data;
};

const deleteDoctor = async (id) => {
    const response = await axios.delete(`${API_URL}/delete_doctor/${id}`);
    return response.data;
};

export {
    getDoctors,
    registerDoctor,
    findDoctorById,
    findDoctorByEmail,
    loginDoctor,
    updateDoctor,
    deleteDoctor,
};

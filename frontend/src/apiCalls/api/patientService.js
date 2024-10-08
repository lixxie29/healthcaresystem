import axios from 'axios';

const API_URL = 'http://localhost:3000';

const getPatients = async () => {
    const response = await axios.get(`${API_URL}/patients`);
    return response.data;
};

const registerPatient = async (patientData) =>{
    const response = await axios.post(`${API_URL}/register_patient`, patientData);
    return response.data;
};

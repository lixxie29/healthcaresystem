import React, { useState } from 'react';
import { registerDoctor } from '../apiCalls/api/doctorService';

const RegisterDoctor = () => {
    const [doctorData, setDoctorData] = useState({
        firstName: '',
        lastName: '',
        age: '',
        gender: '',
        hospitalName: '',
        email: '',
        password: '',
        specialtyId: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setDoctorData({ ...doctorData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await registerDoctor(doctorData);
            alert('Doctor registered successfully');
            // Reset form or redirect as necessary
        } catch (error) {
            console.error('Error registering doctor:', error);
            alert('Registration failed');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input name="firstName" onChange={handleChange} placeholder="First Name" required />
            <input name="lastName" onChange={handleChange} placeholder="Last Name" required />
            <input name="age" onChange={handleChange} placeholder="Age" required type="number" />
            <input name="gender" onChange={handleChange} placeholder="Gender" required />
            <input name="hospitalName" onChange={handleChange} placeholder="Hospital Name" required />
            <input name="email" onChange={handleChange} placeholder="Email" required type="email" />
            <input name="password" onChange={handleChange} placeholder="Password" required type="password" />
            <input name="specialtyId" onChange={handleChange} placeholder="Specialty ID" required type="number" />
            <button type="submit">Register Doctor</button>
        </form>
    );
};

export default RegisterDoctor;

import React, { useState } from 'react';
import { registerDoctor } from '../../apiCalls/api/doctorService';

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

    const [submitted, setSubmitted] = useState(false);
    const [error, setError] = useState(false);

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

    const successMessage = () => {
        return (
            <div
                className="success"
                style={{
                    display: submitted ? "" : "none",
                }}
            >
                <h1>Doctor successfully registered!!</h1>
            </div>
        );
    };

    const errorMessage = () => {
        return (
            <div
                className="error"
                style={{
                    display: error ? "" : "none",
                }}
            >
                <h1>Please fill in all fields</h1>
            </div>
        );
    };

    // return (
    //     <form onSubmit={handleSubmit}>
    //         <input name="firstName" onChange={handleChange} placeholder="First Name" required />
    //         <input name="lastName" onChange={handleChange} placeholder="Last Name" required />
    //         <input name="age" onChange={handleChange} placeholder="Age" required type="number" />
    //         <input name="gender" onChange={handleChange} placeholder="Gender" required />
    //         <input name="hospitalName" onChange={handleChange} placeholder="Hospital Name" required />
    //         <input name="email" onChange={handleChange} placeholder="Email" required type="email" />
    //         <input name="password" onChange={handleChange} placeholder="Password" required type="password" />
    //         <input name="specialtyId" onChange={handleChange} placeholder="Specialty ID" required type="number" />
    //         <button type="submit">Register Doctor</button>
    //     </form>
    // );



    return (
        <div className="form">
            <div>
                <h1>Doctor Registration</h1>
            </div>

            {/* Calling to the methods */}
            <div className="messages">
                {errorMessage()}
                {successMessage()}
            </div>

            <form onSubmit={handleSubmit}>
                <input name="firstName" onChange={handleChange} placeholder="First Name" required/>
                <input name="lastName" onChange={handleChange} placeholder="Last Name" required/>
                <input name="age" onChange={handleChange} placeholder="Age" required type="number"/>
                <input name="gender" onChange={handleChange} placeholder="Gender" required/>
                <input name="hospitalName" onChange={handleChange} placeholder="Hospital Name" required/>
                <input name="email" onChange={handleChange} placeholder="Email" required type="email"/>
                <input name="password" onChange={handleChange} placeholder="Password" required type="password"/>
                <input name="specialtyId" onChange={handleChange} placeholder="Specialty ID" required type="number"/>
                <button onClick={handleSubmit} className="btn" type="submit">Register Doctor</button>
            </form>
        </div>
    );
};

export default RegisterDoctor;

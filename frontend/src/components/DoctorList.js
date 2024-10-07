import React, { useEffect, useState } from 'react';
import { getDoctors } from '../apiCalls/api/doctorService';

const DoctorList = () => {
    const [doctors, setDoctors] = useState([]);

    useEffect(() => {
        const fetchDoctors = async () => {
            try {
                const data = await getDoctors();
                setDoctors(data);
            } catch (error) {
                console.error('Error fetching doctors:', error);
            }
        };

        fetchDoctors();
    }, []);

    return (
        <div>
            <h1>Doctors List</h1>
            <ul>
                {doctors.map((doctor) => (
                    <li key={doctor.id}>
                        {doctor.firstName} {doctor.lastName}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default DoctorList;

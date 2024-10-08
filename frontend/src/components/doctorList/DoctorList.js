import React, { useEffect, useState } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import Box from '@mui/material/Box';
import { getDoctors } from '../../apiCalls/api/doctorService';
import {DoctorListGrid} from "./doctorListGrid";
import {columns} from "./doctorListCols";

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


    const rows = [
        { id: 1, firstName: 'Jon',      lastName: 'Snow',       age: 14,    gender: 'male',     hospitalName:'Medlife', specialtyId: 3, email: 'jon.snow@medlife.ro', password: 'jon123'},
        { id: 2, firstName: 'Cersei',   lastName: 'Lannister', age: 31     , gender:'female' , hospitalName: 'Clearwater Wellness Institute', specialtyId: 1, email: 'cersei@clearwatermed.com', password: 'cersei123'  },
        { id: 3, firstName: 'Jaime',    lastName: 'Lannister',   age: 31   , gender:'male' , hospitalName: 'Clearwater Wellness Institute', specialtyId: 2, email: 'jaime@clearwatermed.com', password: 'jaime123'  },
        { id: 4, firstName: 'Arya',     lastName: 'Stark',     age: 11     , gender:'female' , hospitalName: 'Evergreen Care Hospital', specialtyId: 3, email: 'arya@evergreenmed.com', password: 'arya123'  },
        { id: 5, firstName: 'Daenerys', lastName: 'Targaryen',    age: null, gender:'female' , hospitalName: 'BrightPath Medical Center', specialtyId: 4, email: 'daenerys@brightpathmed.com', password: 'daenerys123'  },
        { id: 6, firstName: 'Mel',       lastName: 'Melisandre',  age: 150  , gender:'female' , hospitalName: 'Lakeside Health Pavilion', specialtyId: 5, email: 'melisandre@lakesidemed.com', password: 'mel123'  },
        { id: 7, firstName: 'Ferrara',  lastName: 'Clifford',    age: 44   , gender:'female' , hospitalName: 'Cedar Valley General Hospital', specialtyId: 1, email: 'ferrara@cedarvaleymed.com', password: 'ferrara123'  },
        { id: 8, firstName: 'Rossini',  lastName: 'Frances',     age: 36   , gender:'male' , hospitalName: 'BlueSky Family Health Clinic', specialtyId: 2, email: 'rossini@blueskymed.com', password: 'ross123'  },
        { id: 9, firstName: 'Harvey',   lastName: 'Roxie',       age: 65   , gender:'male' , hospitalName: 'Summit Grove Medical', specialtyId: 4, email: 'harvey@summitmed.com', password: 'harvey123'  },
    ];

    return (
        <Box sx={{ height: 400, width: '100%' }}>
            <DoctorListGrid
                rows={rows}
                columns={columns}
            />
        </Box>
    );
}

//     return (
//         <div>
//             <h1>Doctors List</h1>
//             <ul>
//                 {doctors.map((doctor) => (
//                     <li key={doctor.id}>
//                         {doctor.firstName} {doctor.lastName}
//                     </li>
//                 ))}
//             </ul>
//         </div>
//     );
// };
//
export default DoctorList;

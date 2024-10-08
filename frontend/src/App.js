import React from 'react';
import DoctorList from './components/doctorList/DoctorList';
import RegisterDoctor from './components/auth/RegisterDoctor';

const App = () => {
  return (
      <div>
        <h1>Healthcare Management System</h1>
        <RegisterDoctor />
        {/*<DoctorList />*/}
      </div>
  );
};

export default App;

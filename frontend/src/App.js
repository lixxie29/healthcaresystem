import React from 'react';
import DoctorList from './components/DoctorList';
import RegisterDoctor from './components/RegisterDoctor';

const App = () => {
  return (
      <div>
        <h1>Healthcare Management System</h1>
        <RegisterDoctor />
        <DoctorList />
      </div>
  );
};

export default App;

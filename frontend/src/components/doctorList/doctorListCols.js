export const columns= [
    { field: 'id', headerName: 'ID', width: 50 },
    {
        field: 'firstName',
        headerName: 'First name',
        width: 100,
        editable: true,
    },
    {
        field: 'lastName',
        headerName: 'Last name',
        width: 100,
        editable: true,
    },
    {
        field: 'age',
        headerName: 'Age',
        type: 'number',
        width: 50,
        editable: true,
    },
    {
        field: 'gender',
        headerName: 'Gender',
        width: 70,
        editable: true,
    },
    {
        field: 'hospitalName',
        headerName: 'Hospital',
        width: 150,
        editable: true,
    },
    {
        field: 'specialtyId',
        headerName: 'Specialty Id',
        type: 'number',
        width: 80,
        editable: true,
    },
    {
        field: 'email',
        headerName: 'Email',
        width: 200,
        editable: true,
    },
    {
        field: 'fullName',
        headerName: 'Full name',
        description: 'This column has a value getter and is not sortable.',
        sortable: false,
        width: 160,
        valueGetter: (value, row) => `${row.firstName || ''} ${row.lastName || ''}`,
    },
];
import {DataGrid} from "@mui/x-data-grid";
import React from "react";

export function DoctorListGrid ({rows, columns})
{
    return (
        <DataGrid
        rows={rows}
        columns={columns}
        initialState={{
            pagination: {
                paginationModel: {
                    pageSize: 5,
                },
            },
        }}
        pageSizeOptions={[5]}
        checkboxSelection
        disableRowSelectionOnClick
    />);
}


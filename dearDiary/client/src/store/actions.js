import {createAction} from "@reduxjs/toolkit"

export const api_start = createAction("API/START");
export const api_end = createAction("API/END");
export const api_error = createAction("API/ERROR");
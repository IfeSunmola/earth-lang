import {createTheme} from "@mui/material/styles";
import axios from "axios";

export const apiClient = axios.create({
	baseURL: "http://localhost:8080/",
});

export const darkTheme = createTheme({
	palette: {
		mode: 'dark',
	},
});

export type RunResponse = {
	isSuccess: boolean;
	msg: string;
}

export type Sample = {
	name: string;
	code: string;
}



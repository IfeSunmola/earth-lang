import './App.css'
import CodeMirror from '@uiw/react-codemirror';
import * as React from "react";
import {useCallback, useEffect, useState} from "react";
import {ThemeProvider} from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import {
	Box,
	Button,
	FormControl,
	InputLabel,
	Link,
	MenuItem,
	Select,
	type SelectChangeEvent,
	Typography
} from '@mui/material';
import {apiClient, darkTheme, type RunResponse, type Sample} from "./Utils.ts";

// No, I'm not splitting this into different components
function App() {
	const [codeInput, setCodeInput] = useState<string>('');
	const [samplesList, setSamplesList] = useState<Sample[]>([]);
	const [selectedSample, setSelectedSample] = useState<string>("");
	const [runResponse, setRunResponse] = useState<RunResponse>();
	const onCodeInputChange = useCallback((val: React.SetStateAction<string>) => {
		setCodeInput(val);
	}, []);
	const onSelectedSampleChange = (event: SelectChangeEvent) => {
		// set the newly selected sample name
		const selectedSampleName = event.target.value as string;
		setSelectedSample(selectedSampleName);

		// find the code for the newly selected sample
		const selectedSampleCode = samplesList
			.find(sample => sample.name === selectedSampleName);
		if (!selectedSampleCode) {
			console.error("Selected sample code not found");
			return;
		}
		setCodeInput(selectedSampleCode.code);
	};
	const onClearBtnClick = () => {
		// change selected sample to the first one in the list, which is
		// select one/empty main
		setSelectedSample(samplesList[0].name);
		setCodeInput(samplesList[0].code);
	}
	const onRunBtnClick = () => {
		const compile = async () => {
			try {
				const response: RunResponse = (await apiClient.post('run', codeInput, {
					headers: {
						'Content-Type': 'text/plain'
					}
				})).data;
				setRunResponse(response);
			} catch (error) {
				console.error("Error running code:", error);
			}
		}
		compile().then();
	}

	useEffect(() => {
		const fetchData = async () => {
			try {
				const response: Sample[] = (await apiClient.get('samples')).data;

				setSamplesList(response);
				if (response.length > 0) {
					setSelectedSample(response[1].name);
					setCodeInput(response[1].code);
				}
			} catch (error) {
				console.log("Error fetching data:", error);
			}
		};
		fetchData().then();
	}, []);

	return (
		<ThemeProvider theme={darkTheme}>
			<CssBaseline/>
			<Box className="container">
				<Box className="header">
					<Typography variant="h3">The Earth Playground</Typography>
					<Typography variant="subtitle1">
						*Clearly a good choice of naming
					</Typography>
					<Link href="https://github.com/IfeSunmola/earth-lang"
						  underline="always" target={"_blank"}>
						Source Code
					</Link>
				</Box>

				<Box className="above-code">
					<Box className="run-clear-btns" onClick={onRunBtnClick}>
						<Button variant="contained">
							Run
						</Button>
						<Button variant="contained" color="error"
								onClick={onClearBtnClick}>
							Clear
						</Button>
					</Box>

					<FormControl size={"small"}>
						<InputLabel id="samples-Id">Samples</InputLabel>
						<Select
							labelId="samples-Id"
							id="demo-simple-select"
							label="Samples"
							value={selectedSample}
							autoWidth={true}
							onChange={onSelectedSampleChange}>

							{samplesList.map((sample: Sample) => (
								<MenuItem value={sample.name}>
									{sample.name}
								</MenuItem>
							))}
						</Select>
					</FormControl>
				</Box>

				<Box className="code-box">
					<CodeMirror value={codeInput} height="500px"
								onChange={onCodeInputChange}
								theme={"dark"}/>
				</Box>

				<Box className="output-box">
					<Typography variant="h5">Output</Typography>
					{runResponse && <p
                      className={runResponse.isSuccess ? 'success' : 'error'}>
						{runResponse.msg}
                    </p>
					}
				</Box>
			</Box>
		</ThemeProvider>
	)
}

export default App

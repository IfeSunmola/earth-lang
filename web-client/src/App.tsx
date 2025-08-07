import './App.css'
import CodeMirror from '@uiw/react-codemirror';
import * as React from "react";
import {useCallback, useState} from "react";
import {createTheme, ThemeProvider} from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import {
	Button,
	FormControl,
	InputLabel,
	Link,
	MenuItem,
	Select,
	type SelectChangeEvent
} from '@mui/material';

const darkTheme = createTheme({
	palette: {
		mode: 'dark',
	},
});

function App() {
	const [code, setCode] = useState("console.log('hello world!');");
	const [sample, setSample] = React.useState('Fibonacci');
	const onCodeChange = useCallback((val: React.SetStateAction<string>) => {
		setCode(val);
	}, []);
	const onSampleChange = (event: SelectChangeEvent) => {
		setSample(event.target.value as string);
	};
	return (
		<ThemeProvider theme={darkTheme}>
			<CssBaseline/>
			<div className="container">
				<div className="header">
					<h1>The Earth Playground</h1>
					<p>*Clearly a good choice of naming</p>
					<Link href="https://github.com/IfeSunmola/earth-lang"
						  underline="always" target={"_blank"}>
						Source Code
					</Link>
				</div>

				<div className="above-code">
					<div className="run-clear-btns">
						<Button variant="contained">Run</Button>
						<Button variant="contained">Clear</Button>
					</div>

					<FormControl size={"small"}>
						<InputLabel id="samples-Id">Samples</InputLabel>
						<Select
							labelId="samples-Id"
							id="demo-simple-select"
							label="Samples"
							value={sample}
							autoWidth={true}
							onChange={onSampleChange}>
							<MenuItem value={"Hello World!"}>Hello
								World!</MenuItem>
							<MenuItem value={"Fibonacci"}>Fibonacci</MenuItem>
							<MenuItem value={"Loops"}>Loops</MenuItem>
						</Select>
					</FormControl>
				</div>

				<div className="code-box">
					<CodeMirror value={code} height="500px"
								onChange={onCodeChange}
								theme={"dark"}/>
				</div>

			</div>
		</ThemeProvider>
	)
}

export default App

import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Box from '@mui/material/Box';
import EmailIcon from '@mui/icons-material/Email';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import {createTheme, ThemeProvider} from '@mui/material/styles';
import axios from "axios";
import {Alert, Collapse} from "@mui/material";
import {emailAppTheme} from "./EmailAppTheme";

function Copyright(props) {
    return (
        <Typography variant="body2" color="text.secondary" align="center" {...props}>
            {'Copyright © '}
            <Link color="inherit" href="https://arasdenizhan.github.io">
                Denizhan Aras
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

const theme = createTheme();

export default function SendEmail() {
    const [completed, setCompleted] = React.useState(false);
    const [ok, setOk] = React.useState(false);
    const [hasError, setHasError] = React.useState(false);
    const [errorMessage, setErrorMessage] = React.useState("No Error");

    const handleSubmit = (event) => {
        event.preventDefault();
        setOk(prevState => !prevState)
        const data = new FormData(event.currentTarget);
        const mailDto = JSON.stringify({
            sender: data.get('senderEmail'),
            receiver: data.get('receiverEmail'),
            subject: data.get('subject'),
            text: data.get('message')
        });
        axios.post('http://localhost:8080/api/email/', mailDto, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            if(response.status===200){
                setOk(prevState => !prevState)
                setCompleted(prevState => !prevState)
                setTimeout( function() { setCompleted(prevState => !prevState) }, 3000);

            }
        }).catch((error) => {
            if( error.response ){
                setOk(prevState => !prevState)
                setErrorMessage(error.response.data)
                setHasError(prevState => !prevState)
                setTimeout( function() { setHasError(prevState => !prevState) }, 3000);
            }
        })
    };

    return (
        <ThemeProvider theme={emailAppTheme}>
            <Collapse in={ok}>
                <Alert variant="filled" severity="info" >
                    Mail going to be sent..
                </Alert>
            </Collapse>
            <Collapse in={completed}>
                <Alert variant="filled" severity="success" aria-hidden={true}>
                    Mail has been sent!
                </Alert>
            </Collapse>
            <Collapse in={hasError}>
                <Alert variant="filled" severity="error" aria-hidden={true}>
                    Operation failed! {errorMessage}
                </Alert>
            </Collapse>
            <Container component="main" maxWidth="xs">
                <CssBaseline/>
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{m: 1, bgcolor: 'secondary.main'}}>
                        <EmailIcon/>
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Denzhn Email Application
                    </Typography>
                    <Box component="form" onSubmit={handleSubmit} noValidate sx={{mt: 1}}>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="senderEmail"
                            label="Sender Email"
                            name="senderEmail"
                            autoFocus
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            name="receiverEmail"
                            label="Receiver Email"
                            type="receiverEmail"
                            id="receiverEmail"
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            name="subject"
                            label="Subject"
                            type="subject"
                            id="subject"
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            name="message"
                            label="Message"
                            type="message"
                            id="message"
                        />
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{mt: 3, mb: 2}}
                        >
                            Send Email
                        </Button>
                    </Box>
                </Box>
                <Copyright sx={{mt: 8, mb: 4}}/>
            </Container>
        </ThemeProvider>
    );
}
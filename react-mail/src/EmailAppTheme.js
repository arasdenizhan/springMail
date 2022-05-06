import { createTheme } from '@mui/material/styles';

export const emailAppTheme = createTheme({
    palette: {
        type: 'light',
        primary: {
            main: '#424242',
            contrastText: '#fafafa',
        },
        secondary: {
            main: '#212121',
        },
        background: {
            default: '#fff176',
        },
        text: {
            primary: '#212121',
        },
    },
});
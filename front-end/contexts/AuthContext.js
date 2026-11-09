'use client'
import React, {createContext, useEffect, useState} from 'react';

export const AuthContext = createContext();

export const AuthContextProvider = ({children}) => {

    const [memberId, setMemberId] = useState('');
    const [accessToken, setAccessToken] = useState('');
    const [isInitialized, setIsInitialized] = useState(false);

    useEffect(() => {
        try {
            const savedMemberId = localStorage.getItem('memberId');
            const savedAccessToken = localStorage.getItem('accessToken');
            if (savedMemberId) {
                setMemberId(savedMemberId);
            }
            if (savedAccessToken) {
                setAccessToken(savedAccessToken);
            }

            
        } catch (error) {
            console.error('인증 정보를 localStorage에서 불러오지 못했습니다.', error);
        } finally {
            setIsInitialized(true);
        }
    }, []);

    useEffect(() => {
        if (!isInitialized) return;

        try {
            if (memberId) {
                window.localStorage.setItem('memberId', memberId);
            } else {
                window.localStorage.removeItem('memberId');
            }
            if (accessToken) {
                window.localStorage.setItem('accessToken', accessToken);
            } else {
                window.localStorage.removeItem('accessToken');
            }
        } catch (error) {
            console.error('인증 정보를 localStorage에 저장하지 못했습니다.', error);
        }
    }, [memberId, accessToken, isInitialized]);

    const value = {
        memberId,
        accessToken,
        setMemberId,
        setAccessToken,
        isInitialized
    }

    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    );
};

export default AuthContextProvider;

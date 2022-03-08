import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';

// components
import Memory from '../../components/Memory';
import MemoryJarPreview from '../../components/MemoryJarPreview';

// services
import * as memoryJarService from '../../services/memoryJarService';

import './styles.scss'

const Home = (props) => {
    const { currentUser } = props
    const [memoryJars, setMemoryJars] = useState([])

    useEffect(() => {
        const getMemoryJars = async () => {
            const ownerJars = await memoryJarService.getJarsByOwner(currentUser.id);
            setMemoryJars(ownerJars.data);
        };

        getMemoryJars();
    }, [currentUser.id])

    return (
        <div className='wrapper'>
            <h2>Favorite Memories</h2>
            <Memory />
            <h2>Recent Memories</h2>
            <Memory />
            <div className='memory-jar-previews'>
                {memoryJars?.map(jar => <MemoryJarPreview key={jar.jarId} jarId={jar.jarId}/>)}
            </div>
        </div>
    );
};

Home.defaultProps = {
    currentUser: null
}

const mapStateToProps = ({ user }) => ({
    currentUser: user.currentUser
});

export default connect(mapStateToProps, null)(Home);
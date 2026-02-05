package de.riversroses.missions.db;

import de.riversroses.missions.dto.MissionDto;

import java.util.HashMap;

public class DatabaseMissionRepository implements MissionRepository {

    @Override
    public boolean removeMission(long missionId) {
        return false;
    }

    @Override
    public void addMission(Long id, MissionDto mission) {

    }

    @Override
    public HashMap<Long, MissionDto> getMissions() {
        return null;
    }
}

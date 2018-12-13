/*
 * Copyright (C) 2018. Mikhail Kulesh
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details. You should have received a copy of the GNU General
 * Public License along with this program.
 */

package com.mkulesh.onpc.iscp.messages;

import com.mkulesh.onpc.R;
import com.mkulesh.onpc.iscp.EISCPMessage;
import com.mkulesh.onpc.iscp.ISCPMessage;

/*
 * Master Volume Command
 */
public class MasterVolumeMsg extends ISCPMessage
{
    public final static String CODE = "MVL";

    public enum Command implements StringParameterIf
    {
        UP(R.string.master_volume_up, R.drawable.volume_amp_up),
        DOWN(R.string.master_volume_down, R.drawable.volume_amp_down),
        UP1(R.string.master_volume_up1, R.drawable.volume_amp_up),
        DOWN1(R.string.master_volume_down1, R.drawable.volume_amp_down);

        final int descriptionId;
        final int imageId;

        Command(final int descriptionId, final int imageId)
        {
            this.descriptionId = descriptionId;
            this.imageId = imageId;
        }

        public String getCode()
        {
            return toString();
        }

        public int getDescriptionId()
        {
            return descriptionId;
        }

        public int getImageId()
        {
            return imageId;
        }
    }

    private final Command command;

    MasterVolumeMsg(EISCPMessage raw) throws Exception
    {
        super(raw);
        command = null;
    }

    public MasterVolumeMsg(Command level)
    {
        super(0, null);
        this.command = level;
    }

    public Command getCommand()
    {
        return command;
    }

    @Override
    public String toString()
    {
        return CODE + "[" + data + "; CMD=" + (command != null? command.toString() : "null") + "]";
    }

    @Override
    public EISCPMessage getCmdMsg()
    {
        return new EISCPMessage('1', CODE, command.getCode());
    }

    @Override
    public boolean hasImpactOnMediaList()
    {
        return false;
    }
}
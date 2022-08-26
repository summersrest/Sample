package ${packageImport};

import ${basePackage}.base.activity.BaseFragment;
import ${basePackage}.databinding.${bindingName};

/**
 * @author liujiang
 * created at: ${.now}
 * Desc: ${desc}
 */
public class ${fragmentName} extends BaseFragment<${bindingName}> {

    @Override
    protected ${bindingName} getViewBinding() {
        return ${bindingName}.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {

    }

}
